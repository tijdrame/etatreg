import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { Bp2ComUpdateComponent } from 'app/entities/bp-2-com/bp-2-com-update.component';
import { Bp2ComService } from 'app/entities/bp-2-com/bp-2-com.service';
import { Bp2Com } from 'app/shared/model/bp-2-com.model';

describe('Component Tests', () => {
  describe('Bp2Com Management Update Component', () => {
    let comp: Bp2ComUpdateComponent;
    let fixture: ComponentFixture<Bp2ComUpdateComponent>;
    let service: Bp2ComService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [Bp2ComUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(Bp2ComUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(Bp2ComUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(Bp2ComService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Bp2Com(123);
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
        const entity = new Bp2Com();
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
