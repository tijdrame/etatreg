import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { Bp3HisUpdateComponent } from 'app/entities/bp-3-his/bp-3-his-update.component';
import { Bp3HisService } from 'app/entities/bp-3-his/bp-3-his.service';
import { Bp3His } from 'app/shared/model/bp-3-his.model';

describe('Component Tests', () => {
  describe('Bp3His Management Update Component', () => {
    let comp: Bp3HisUpdateComponent;
    let fixture: ComponentFixture<Bp3HisUpdateComponent>;
    let service: Bp3HisService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [Bp3HisUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(Bp3HisUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(Bp3HisUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(Bp3HisService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Bp3His(123);
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
        const entity = new Bp3His();
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
