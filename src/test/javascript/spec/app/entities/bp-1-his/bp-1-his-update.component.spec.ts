import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { Bp1HisUpdateComponent } from 'app/entities/bp-1-his/bp-1-his-update.component';
import { Bp1HisService } from 'app/entities/bp-1-his/bp-1-his.service';
import { Bp1His } from 'app/shared/model/bp-1-his.model';

describe('Component Tests', () => {
  describe('Bp1His Management Update Component', () => {
    let comp: Bp1HisUpdateComponent;
    let fixture: ComponentFixture<Bp1HisUpdateComponent>;
    let service: Bp1HisService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [Bp1HisUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(Bp1HisUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(Bp1HisUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(Bp1HisService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Bp1His(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Bp1His();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
